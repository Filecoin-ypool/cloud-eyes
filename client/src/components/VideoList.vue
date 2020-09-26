<template>
    <div class="video-list">
        <div v-for="(item,index) in list" :key="index" @click="play(item)">
            <img :src="item.img" alt="">
            <span class="video-name">{{item.fileName}}</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: "VideoList",
        props: ['list'],
        data() {
            return {
                //     list: [
                //         {
                //             img: require(`@/assets/home/img-video.png`),
                //             name: '2020100201'
                //         }, {
                //             img: require(`@/assets/home/img-video.png`),
                //             name: '2020100201'
                //         }, {
                //             img: require(`@/assets/home/img-video.png`),
                //             name: '2020100201'
                //         }, {
                //             img: require(`@/assets/home/img-video.png`),
                //             name: '2020100201'
                //         }, {
                //             img: require(`@/assets/home/img-video.png`),
                //             name: '2020100201'
                //         }, {
                //             img: require(`@/assets/home/img-video.png`),
                //             name: '2020100201'
                //         }, {
                //             img: require(`@/assets/home/img-video.png`),
                //             name: '2020100201'
                //         }, {
                //             img: require(`@/assets/home/img-video.png`),
                //             name: '2020100201'
                //         }, {
                //             img: require(`@/assets/home/img-video.png`),
                //             name: '2020100201'
                //         },
                //     ]
            }
        },
        watch: {
            list: function () {
                this.list.forEach(item => {
                    item.img = require(`@/assets/home/img-video.png`)
                })
            }
        },
        methods: {
            //播放
            play(item) {
                console.log(item)
            }
        }
    }
</script>

<style scoped>
    .video-list {
        margin: 50px 120px;
        display: flex;
        flex-wrap: wrap;
        max-height: 350px;
        overflow-y: scroll;
    }

    .video-list > div {
        display: flex;
        flex-direction: column;
        align-items: center;
        font-size: 16px;
        margin-right: 40px;
        margin-bottom: 20px;
    }

    .video-list > div:hover {
        cursor: pointer;
    }

    .video-name {
        margin-top: 20px;
    }

    img {
        width: 168px;
        height: 168px;
    }
</style>
