<template>
    <div>
        <Modal
                :closable="false"
                :mask-closable="false"
                v-model="modal"
                title="upload video file"
                @on-cancel="cancel"
                cancel-text="cancel"
                ok-text="ok"
                @on-ok="ok">
            <div class="miner">
                <span>miner</span>
                <Input v-model="formItem.miner" size="large"/>
            </div>
            <div class="upload-button">
                <Upload
                        :default-file-list="defaultList"
                        :on-success="response =>onSuccess(response)"
                        size="large"
                        :headers="{
                            token
                        }"
                        :data="formItem"
                        action="/api/storage_deal/upload">
                    <Button icon="ios-cloud-upload-outline">upload</Button>
                </Upload>
            </div>
        </Modal>
    </div>
</template>

<script>
    export default {
        name: "VideoUpload",
        data() {
            return {
                file: null,
                loadingStatus: false,
                formItem: {
                    miner: 't01800'
                },
                token: '',
                defaultList: []
            }
        },
        props: ['modal'],
        mounted() {
            this.getToken()
        },
        methods: {
            cancel() {
                this.$parent.uploadCancel()
            },
            ok() {
                this.$parent.uploadCancel()
            },
            getToken() {
                const token = localStorage.getItem("token")
                if (token != 'undefined' && token != null) {
                    this.token = token
                } else {
                    this.token = null
                }
            },
            //上传成功
            onSuccess(res) {
                console.log("文件,", res)
                this.defaultList = []
                this.$Message.success(res.msg)
            }
        }
    }
</script>

<style scoped>
    .miner {
        margin-bottom: 20px;
        display: flex;
        align-items: center;
        font-size: 20px;
    }

    .miner > span {
        margin-right: 10px;
    }

    .upload-button {
        display: flex;
        flex-direction: row-reverse;
    }
</style>
