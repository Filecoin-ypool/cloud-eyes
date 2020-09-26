<template>
    <div>
        <div class="eyes-header">
            <div>
                <img src="../assets/logo.png" alt="">
            </div>
            <div>
                <Upload action="//jsonplaceholder.typicode.com/posts/" class="upload">
                    <Button icon="ios-cloud-upload-outline" type="primary" size="large">Upload Surveillance Video
                    </Button>
                </Upload>
                <span class="item" @click="signIn">Sign in</span>
                <span class="item" @click="signUp">Sign up</span>
            </div>
        </div>
        <login-form :modal="modal" :modify="modify"/>
    </div>
</template>

<script>
    import {getClient} from "../utils/lotus";
    import LoginForm from '../components/LoginForm'

    export default {
        name: "EyesHeader",
        mounted() {
            // this.getAddress()
        },
        data() {
            return {
                modal: false,
                modify: {}
            }
        },
        components: {
            LoginForm
        },
        methods: {
            //登录
            signIn() {
                this.modal = true
                let modify = {
                    title: "Sign up"
                }
                this.modify = modify
            },
            //注册
            signUp() {
                this.modal = true
            },
            async getAddress() {
                const nodeClient = getClient();
                const defaultWalletAddress = await nodeClient.walletDefaultAddress();
                console.log("address", defaultWalletAddress)
            },
            cancel() {
                this.modal = false
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
