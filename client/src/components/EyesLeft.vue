<template>
    <div class="eyes-left">
        <span class="sort">Sort</span>
        <div class="item-wrapper">
            <span :class="ins==index?'item-active':''"
                  v-for="(item,index) in list"
                  :key="index" @click="changeDate(index,item)">{{item.name}}</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: "EyesLeft",
        data() {
            return {
                ins: 0,
                list: [
                    {
                        name: "2020/10/01"
                    }, {
                        name: "2020/09/30"
                    }, {
                        name: "2020/09/30"
                    }, {
                        name: "2020/09/30"
                    }
                ]
            }
        },
        methods: {
            changeDate(index, item) {
                this.ins = index
                console.log(item.name)
            }
        }
    }
</script>

<style scoped>
    .eyes-left {
        background-color: white;
        height: 100vh;
        border-top: 5px solid #F6F6F6;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-top: 50px;
        overflow-y: scroll;
    }

    .item-wrapper {
        display: flex;
        flex-direction: column;
    }

    .sort {
        font-size: 24px;
        color: #000000;
        margin-bottom: 22px;
    }

    .item-wrapper > span {
        font-size: 18px;
        width: 250px;
        line-height: 60px;
        height: 60px;
        text-align: center;
        margin-left: 30px;
    }

    .item-wrapper > span:hover {
        cursor: pointer;
    }

    .item-active {
        background: #F6F6F6;
        border-left: 6px solid #31B7CB;
    }
</style>
