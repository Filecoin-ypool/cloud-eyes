<template>
    <div class="eyes-header">
        <div>
            <img src="../assets/logo.png" alt="">
        </div>
        <div>
            <span class="item">Sign in</span>
            <span class="item">Sign up</span>
        </div>
    </div>
</template>

<script>
    import {getClient} from "../utils/lotus";

    export default {
        name: "EyesHeader",
        mounted() {
            // this.getAddress()
        },
        methods: {
            async getAddress() {
                const nodeClient = getClient();
                const defaultWalletAddress = await nodeClient.walletDefaultAddress();
                console.log("address", defaultWalletAddress)
            }
        }
    }
</script>

<style scoped>
    .eyes-header {
        height: 60px;
        width: 100%;
        background: #FFFFFF;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .eyes-header > div:first-child {
        text-align: center;
        width: 50%;
    }

    .eyes-header > div:last-child {
        margin-right: 200px;
        width: 200px;
        display: flex;
        justify-content: space-between;
    }

    .item {
        width: 82px;
        height: 32px;
        background: #31B7CB;
        border-radius: 16px;
        text-align: center;
        line-height: 32px;
        font-size: 18px;
        font-family: Source Han Sans CN;
        font-weight: 500;
        color: #FFFFFF;

    }

</style>
