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
                    <input type="text" placeholder="Username">
                    <input type="password" placeholder="Password">
                </div>
            </div>
            <div slot="footer">
                <Button type="primary" size="large" @click="handleSubmit('formValidate')">Sign in</Button>
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
            },
        },
        data() {
            return {
                formItem: {
                    username: '',
                    password: '',
                },
            }
        },
        methods: {
            cancel() {
                this.$parent.cancel();
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
