package uselessJewelsMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import uselessJewelsMod.UselessJewelsMod;
import uselessJewelsMod.cards.GemstoneAttack;
import uselessJewelsMod.util.TextureLoader;

import static uselessJewelsMod.UselessJewelsMod.makeRelicOutlinePath;
import static uselessJewelsMod.UselessJewelsMod.makeRelicPath;

public class UselessJewelsRelic extends CustomRelic {
    public static final String ID = UselessJewelsMod.makeID("UselessJewelsRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("UselessJewelsRelic.png"));

    public UselessJewelsRelic() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }


    @Override
    public void atTurnStart(){
        flash();;
        AbstractDungeon.player.hand.addToHand(new GemstoneAttack());
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
