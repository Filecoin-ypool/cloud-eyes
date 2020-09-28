<template>
    <div class="video-list">
        <template v-for="(item,index) in list">
            <div @click="play(item)" :key="index" v-if="item.status==5">
                <img :src="item.img" alt="">
                <span style="color: #2DAFE0" class="video-name">{{item.fileName}}</span>
                <span style="color: #2DAFE0">存储成功</span>
            </div>
            <div :key="index" v-else @click="play(item)">
                <img :src="item.img" alt="">
                <span class="video-name">{{item.fileName}}</span>
                <span>存储中...</span>
            </div>
        </template>
    </div>
</template>

<script>
    export default {
        name: "VideoList",
        props: ['list'],
        data() {
            return {}
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
                if (item.status != 5) {
                    this.$Message.error('存储中，无法播放');
                } else {
                    this.$emit('event2', item.id)
                }
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
        margin-bottom: 10px;
    }

    img {
        width: 168px;
        height: 168px;
    }
</style>
