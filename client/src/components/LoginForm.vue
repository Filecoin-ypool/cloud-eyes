<template>
    <div>
        <Modal
                :closable="false"
                :mask-closable="false"
                v-model="modal"
                @on-cancel="cancel">
            <div class="login-content">
                <img src="../assets/logo.png" alt="">
                <div class="item-wrapper">
                    <input type="text" placeholder="Username" v-model="formItem.username">
                    <input type="password" placeholder="Password" v-model="formItem.password">
                </div>
            </div>
            <div slot="footer">
                <Button type="primary" size="large" @click="handleSubmit('formValidate')">{{formItem.title}}</Button>
                <Button type="text" size="large" @click="cancel">取消</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
    export default {
        name: "LoginForm",
        props: ["modal", "modify"],
        watch: {
            modify: function () {
                console.log("modify", this.modify)
                this.formItem = this.modify;
            },
        },
        data() {
            return {
                formItem: {
                    username: '',
                    password: '',
                    type: 0,
                    title: 'Sign in'
                },
            }
        },
        methods: {
            cancel() {
                this.$parent.cancel();
            },
            //提交
            async handleSubmit() {
                if (this.formItem.username == '') {
                    this.$Message.error('用户名不能为空');
                } else if (this.formItem.password == '') {
                    this.$Message.error('密码不能为空');
                } else {
                    let form = {
                        username: this.formItem.username,
                        password: this.formItem.password
                    }
                    if (this.formItem.type == 0) { //登录
                        this.login(form)
                    } else {//注册
                        this.register(form)
                    }
                }
            },
            //注册
            async register(form) {
                await this.$api.signUp(form)
                this.$Message.success("注册成功，请重新登录")
                this.$parent.cancel();
            },
            //登录
            async login(form) {
                let token = await this.$api.signIn(form)
                localStorage.setItem("token", token);
                let username = await this.$api.getUsername()
                localStorage.setItem("username", username);
                this.$parent.cancel();
                this.$parent.getUsername()
            }
        }
    }
</script>

<style scoped>

    .login-content {
        margin-left: 20px;
    }

    img {
        margin-top: 20px;
        width: 140px;
        height: 42px;
    }

    .item-wrapper {
        width: 100%;
        justify-content: left;
        margin-top: 40px;
    }

    input {
        width: 500px;
        height: 60px;
        background: #F6F6F6;
        border: 1px solid #F6F6F6;
        border-radius: 8px;
        margin-bottom: 30px;
        font-size: 30px;
        padding: 0 20px;
    }
</style>
