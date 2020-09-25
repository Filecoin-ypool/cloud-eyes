<template>
    <div>
        <eyes-header/>
        <div class="major-wrapper">
            <eyes-left/>
            <div class="content">
                <video-list/>
            </div>
        </div>
    </div>
</template>

<script>
    import EyesHeader from "../components/EyesHeader"
    import EyesLeft from "../components/EyesLeft"
    import VideoList from "../components/VideoList"

    export default {
        name: "Major",
        components: {
            EyesHeader,
            EyesLeft,
            VideoList
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
