package gg.miner.sykoure.ranks.data;

import lombok.Data;

@Data
public class PlayerRank {
    private String uuid;

    private int rankId;

    public PlayerRank(String uuid, int rankId) {
        this.uuid = uuid;
        this.rankId = rankId;
    }
}
