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
                <Select v-model="formItem.miner" style="width:200px">
                    <Option v-for="item in minerList" :value="item" :key="item">{{ item }}</Option>
                </Select>
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
                    miner: ''
                },
                token: '',
                defaultList: [],
                minerList: [
                    't02619',
                    't01800'
                ]
            }
        },
        props: ['modal'],
        mounted() {
            this.getToken()
            this.getMiner()

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
            //获取miner
            async getMiner() {
                let list =await this.$api.getMinerList();
                this.minerList = list
                let index = Math.floor((Math.random() * this.minerList.length));
                this.formItem.miner = this.minerList[index]
            },
            //上传成功
            onSuccess(res) {
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
