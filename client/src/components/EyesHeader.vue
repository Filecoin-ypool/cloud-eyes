<template>
    <div>
        <div class="eyes-header">
            <div>
                <img src="../assets/logo.png" alt="">
            </div>
            <div>
                <Button icon="ios-cloud-upload-outline"
                        type="primary" size="large" @click="upload">
                    Upload Surveillance Video
                </Button>
                <span class="item" @click="signIn" v-if="username==''">Sign in</span>
                <span class="item" @click="signUp" v-if="username==''">Sign up</span>
                <span class="item" v-if="username!=''">{{username}}</span>
                <span class="item" @click="signOut" v-if="username!=''">sign out</span>
            </div>
        </div>
        <login-form :modal="modal" :modify="modify"/>
        <video-upload :modal="uploadModal" ref="myUpload"/>
    </div>
</template>

<script>
    import LoginForm from '../components/LoginForm'
    import VideoUpload from '../components/VideoUpload'

    export default {
        name: "EyesHeader",
        mounted() {
            this.getUsername()
        },
        data() {
            return {
                modal: false,
                modify: {},
                username: '',
                uploadModal: false
            }
        },
        components: {
            LoginForm,
            VideoUpload
        },
        methods: {
            //登录
            signIn() {
                this.modal = true
                let modify = {
                    password: '',
                    title: "Sign in",
                    type: 0  //登录
                }
                this.modify = modify
            },
            //注册
            signUp() {
                this.modal = true
                let modify = {
                    password: '',
                    title: "Sign up",
                    type: 1  //注册
                }
                this.modify = modify
            },
            getUsername() {
                const token = localStorage.getItem("token")
                if (token != 'undefined' && token != null) {
                    this.username = localStorage.getItem("username")
                } else {
                    this.username = ''
                }
                this.$parent.changeType()
                this.$parent.getStatistics()
            },
            //上传
            upload() {
                this.uploadModal = true
                this.$refs.myUpload.getMiner()
                this.$refs.myUpload.getToken()
            },
            //退出
            signOut() {
                localStorage.clear()
                this.getUsername()
            },
            cancel() {
                this.modal = false
            },
            uploadCancel() {
                this.uploadModal = false
            }
        }
    }
</script>

<style scoped>
    .eyes-header {
        height: 60px;
        width: 100%;
        background: #FFFFFF;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .eyes-header > div:first-child {
        text-align: center;
        width: 50%;
    }

    .eyes-header > div:last-child {
        margin-right: 200px;
        width: 480px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .upload {
        display: flex;
    }

    .item {
        width: 82px;
        height: 32px;
        background: #31B7CB;
        border-radius: 16px;
        text-align: center;
        line-height: 32px;
        font-size: 18px;
        font-family: Source Han Sans CN;
        font-weight: 500;
        color: #FFFFFF;
    }

    .item:hover {
        cursor: pointer;
    }

</style>
