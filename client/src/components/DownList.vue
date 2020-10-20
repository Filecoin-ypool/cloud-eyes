<template>
    <div class="down-content">
        <div class="down-container">
            <div v-for="(item,index) in list" :key="index" class="down-item">
                <img :src="item.img" alt="">
                <span style="color: #2DAFE0" class="video-name">{{item.fileName}}</span>
                <div>
                    <Button class="button-left" v-if="item.status==1" @click="download(item)" type="primary">Download
                    </Button>
                    <Button class="button-left" v-else-if="item.status==0" disabled="true">applying</Button>
                    <Button v-else @click="apply(item)" type="success">Apply</Button>
                </div>
            </div>
        </div>
        <div class="page">
            <div></div>
            <Page :total="total" :page-size="size" @on-change="pageChange"/>
        </div>
    </div>
</template>

<script>
    export default {
        name: "DownList",
        data() {
            return {
                total: 50,
                size: 20,
                current: 0,
                list: []
            }
        },
        mounted() {
            this.getList()
        },
        methods: {
            async getList() {
                const {list, total} = await this.$api.getList({
                    pageNum: this.current,
                    pageSize: this.size
                })
                list.forEach(item => {
                    item.img = require(`@/assets/home/img-video.png`)
                })
                this.list = list
                this.total = total
            },
            //申请
            async apply(item) {
                await this.$api.downApply(item.id)
                this.getList()
            },
            //下载
            async download(item) {
                await this.$api.download(item.id)
                this.getList()
            },
            //页面更改
            pageChange(num) {
                this.current = num;
                this.getList()
            }
        }
    }
</script>

<style scoped>
    .down-content {
        padding: 50px;
        width: 100%;
        background-color: #F6F6F6;
        height: 100vh;
    }

    .down-container {
        display: flex;
        flex-wrap: wrap;
        width: 100%;
    }

    .down-item {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-right: 20px;
        margin-bottom: 50px;
    }

    .video-name {
        width: 300px;
        margin-bottom: 10px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    img {
        width: 300px;
        margin-bottom: 10px;
    }

    .down-item > span {
        font-size: 22px;
    }

    .button-left {
        margin-right: 20px;
    }

    .page {
        width: 100%;
        display: flex;
        justify-content: space-between;
    }

</style>
