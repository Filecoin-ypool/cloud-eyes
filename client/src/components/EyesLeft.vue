<template>
    <div class="eyes-left">
        <span class="sort">Sort</span>
        <div class="item-wrapper">
            <span :class="ins==index?'item-active':''"
                  v-for="(item,index) in list"
                  :key="index" @click="changeDate(index,item)">{{item.day}}</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: "EyesLeft",
        data() {
            return {
                ins: 0,
                list: []
            }
        },
        mounted() {
            this.getList()
        },
        methods: {
            //获取列表
            async getList() {
                let data = await this.$api.getDayList()
                this.list = data
                this.changeDate(0,data[0])
            },
            async changeDate(index, item) {
                this.ins = index
                let data = await this.$api.getListByDay(item.day)
                console.log("child", data)
                this.$emit('event1', data)
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
