import {LotusRPC} from "@filecoin-shipyard/lotus-client-rpc";
import {BrowserProvider} from "@filecoin-shipyard/lotus-client-provider-browser";
import {testnet} from "@filecoin-shipyard/lotus-client-schema";

export const getClient = () => {
    // API endpoint for local Lotus devnet
    // const API = "ws://localhost:7777";
    const API = "ws://171.214.13.227:7777";

    // Websocket endpoint for local Lotus devnet
    // const wsUrl = API + `/${options.nodeNumber}/${options.nodeOrMiner}/rpc/v0`;
    const wsUrl = API + `/rpc/v0`;

    // Creating and returning a Lotus client that can be used anywhere in the app
    const provider = new BrowserProvider(wsUrl,{
        'token':"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJBbGxvdyI6WyJyZWFkIiwid3JpdGUiLCJzaWduIiwiYWRtaW4iXX0.xr_spMgaANTtevX2ChlDws6pYuIaih8kjejGQHfmBBU"
    });
    return new LotusRPC(provider, {
        schema:
        // options.nodeOrMiner === "node" ? testnet.fullNode : testnet.storageMiner,
        testnet.fullNode
    });
};

