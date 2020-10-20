<template>
    <div>
        <eyes-header/>
        <div class="major-wrapper" v-if="username!='admin'">
            <eyes-left @event1="getList($event)" :type="type"/>
            <div class="content">
                <div class="content-header">
                    <div class="content-sta">
                        <span class="content-sta-title">数据统计</span>
                        <div class="content-sta-success">
                            <span>存储成功容量</span>
                            <span>{{this.success}}</span>
                        </div>
                        <div class="content-sta-total">
                            <span>已传容量</span>
                            <span style="color: #2DAFE0">{{this.total}}</span>
                        </div>
                    </div>
                    <div class="provide">
                        <a href="https://www.ypool.io/major/home" target="_blank">
                            <img class="provide-img" src="../assets/home/logo-ypool.png" alt="">
                        </a>
                        <span>出品</span>
                    </div>
                </div>
                <video-play :id="id"/>
                <video-list :list="list" @event2="getPlayUrl($event)"/>
            </div>
        </div>
        <div v-else>
            <down-list/>
        </div>
    </div>
</template>

<script>
    import EyesHeader from "../components/EyesHeader"
    import EyesLeft from "../components/EyesLeft"
    import VideoList from "../components/VideoList"
    import DownList from '../components/DownList'
    import VideoPlay from "../components/VideoPlay"
    import {getSize} from "../common/util"

    export default {
        name: "Major",
        components: {
            DownList,
            EyesHeader,
            EyesLeft,
            VideoList,
            VideoPlay
        },
        data() {
            return {
                list: [],
                id: '',
                type: '',
                total: 0,
                success: 0,
                username:'',
            }
        },
        mounted() {
            this.getStatistics()
            this.username = localStorage.getItem("username")
        },
        methods: {
            getList(data) {
                this.list = data
                if (this.list.length > 0) {
                    this.list.forEach(item => {
                        if (item == 5) {
                            this.id = item.id
                            return
                        }
                    })
                }
            },
            getPlayUrl(id) {
                this.id = id
            },
            changeType() {
                this.type = Math.ceil(Math.random() * 10)
            },
            //统计数据
            async getStatistics() {
                this.username = localStorage.getItem("username")
                let data = await this.$api.statistics();
                let total = 0
                let success = 0
                await data.forEach(item => {
                    total = Number(total) + Number(item.sum)
                    if (item.status == 7)
                        success = item.sum
                })
                this.total = getSize(total, 2)
                this.success = getSize(success, 2)
            }
        }
    }
</script>

<style scoped>

    .major-wrapper {
        display: flex;
    }

    .content {
        width: 100%;
        background-color: #F6F6F6;
    }

    .content-header {
        padding-left: 220px;
        margin-top: 30px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-right: 230px;
    }

    .provide {
        display: flex;
        align-items: center;
    }

    .provide-img {
        height: 30px;
    }

    .provide > span {
        font-size: 18px;
        color: #C0C0C0;
        font-weight: 500;
        margin-left: 5px;
    }

    .content-sta {
        display: flex;
        align-items: center;
    }

    .content-sta-title {
        font-size: 22px;
        font-weight: 500;
        margin-right: 10px;
    }

    .content-sta-success {
        margin-right: 20px;
        font-size: 18px;
    }

    .content-sta-success > span:first-child {
        margin-right: 5px;
    }

    .content-sta-success > span:last-child {
        margin-right: 5px;
        color: #ee3f3f;
        font-weight: 500;
    }

    .content-sta-total {
        margin-right: 20px;
        font-size: 18px;
    }


    .content-sta-total > span:first-child {
        margin-right: 5px;
    }
</style>


