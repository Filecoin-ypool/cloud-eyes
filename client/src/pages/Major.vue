<template>
    <div>
        <eyes-header/>
        <div class="major-wrapper">
            <eyes-left @event1="getList($event)"/>
            <div class="content">
                <video-play/>
                <video-list :list="list"/>
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
                list: []
            }
        },
        methods: {
            getList(data) {
                console.log("data", data)
                this.list = data
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
