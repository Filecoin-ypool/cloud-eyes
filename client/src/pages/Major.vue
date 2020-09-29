<template>
    <div>
        <eyes-header/>
        <div class="major-wrapper">
            <eyes-left @event1="getList($event)" :type="type"/>
            <div class="content">
                <video-play :id="id"/>
                <video-list :list="list" @event2="getPlayUrl($event)"/>
            </div>
        </div>
    </div>
</template>

<script>
    import EyesHeader from "../components/EyesHeader"
    import EyesLeft from "../components/EyesLeft"
    import VideoList from "../components/VideoList"
    import VideoPlay from "../components/VideoPlay"

    export default {
        name: "Major",
        components: {
            EyesHeader,
            EyesLeft,
            VideoList,
            VideoPlay
        },
        data() {
            return {
                list: [],
                id: '',
                type: ''
            }
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
</style>
