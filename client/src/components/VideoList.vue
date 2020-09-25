<template>
    <div class="video-list">
        <div v-for="(item,index) in list" :key="index">
            <img :src="item.img" alt="">
            <span class="video-name">{{item.name}}</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: "VideoList",
        data() {
            return {
                list: [
                    {
                        img: require(`@/assets/home/img-video.png`),
                        name: '2020100201'
                    }, {
                        img: require(`@/assets/home/img-video.png`),
                        name: '2020100201'
                    }, {
                        img: require(`@/assets/home/img-video.png`),
                        name: '2020100201'
                    }, {
                        img: require(`@/assets/home/img-video.png`),
                        name: '2020100201'
                    }, {
                        img: require(`@/assets/home/img-video.png`),
                        name: '2020100201'
                    }, {
                        img: require(`@/assets/home/img-video.png`),
                        name: '2020100201'
                    }, {
                        img: require(`@/assets/home/img-video.png`),
                        name: '2020100201'
                    }, {
                        img: require(`@/assets/home/img-video.png`),
                        name: '2020100201'
                    }, {
                        img: require(`@/assets/home/img-video.png`),
                        name: '2020100201'
                    },
                ]
            }
        }
    }
</script>

<style scoped>
    .video-list {
        /*margin: 82px 120px;*/
        margin: 50px 120px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        max-height: 350px;
        overflow-y: scroll;
    }

    .video-list > div {
        display: flex;
        flex-direction: column;
        align-items: center;
        font-size: 16px;
        margin-right: 20px;
        margin-bottom: 20px;
    }

    .video-name {
        margin-top: 20px;
    }

    img {
        width: 168px;
        height: 168px;
    }
</style>
