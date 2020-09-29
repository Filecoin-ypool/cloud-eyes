<template>
    <div class="video-play">
        <div class="player-container">
            <video-player class="vjs-custom-skin" :options="playerOptions"></video-player>
        </div>
    </div>
</template>

<script>
    //引入video样式
    import 'video.js/dist/video-js.css'
    import 'vue-video-player/src/custom-theme.css'

    export default {
        name: "VideoPlay",
        data() {
            return {
                playerOptions: {
                    playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
                    autoplay: false, //如果true,浏览器准备好时开始回放。
                    controls: true, //控制条
                    preload: 'auto', //视频预加载
                    muted: false, //默认情况下将会消除任何音频。
                    loop: false, //导致视频一结束就重新开始。
                    language: 'zh-CN',
                    aspectRatio: '16:7', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
                    fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
                    sources: [{
                        type: 'video/mp4',
                        src: ''
                    }],
                    poster: "http://39.106.117.192:8080/static/indexImg.png", //你的封面地址（覆盖在视频上面的图片）
                    width: document.documentElement.clientWidth,
                    notSupportedMessage: '此视频暂无法播放，请稍后再试' //允许覆盖Video.js无法播放媒体源时显示的默认信息。
                },
                address: '',
            }
        },
        props: ['id'],
        watch: {
            id: function () {
                //暂时不播放
                // this.playerOptions.sources[0].src = 'api/storage_deal/get_file?id=' + this.id
            }
        }
    }
</script>

<style scoped>
    .video-play {
        margin-top: 20px;
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .player-container {
        width: 80%;
    }
</style>
