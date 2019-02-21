package uselessJewelsMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import uselessJewelsMod.UselessJewelsMod;
import uselessJewelsMod.cards.GemstoneAttack;
import uselessJewelsMod.cards.GemstonePower;
import uselessJewelsMod.cards.GemstoneSkill;
import uselessJewelsMod.util.TextureLoader;

import static uselessJewelsMod.UselessJewelsMod.logger;
import static uselessJewelsMod.UselessJewelsMod.makeRelicPath;

public class UselessJewelsRelic extends CustomRelic {
    public static final String ID = UselessJewelsMod.makeID("UselessJewelsRelic");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("UselessJewelsRelic.png"));

    private static final int ATTACK_CHANCE = 55;
    private static final int SKILL_CHANCE = 35 + ATTACK_CHANCE;

    public UselessJewelsRelic() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStart(){
        flash();;
        int chance = AbstractDungeon.relicRng.random(100);  // From 0 to 99

        /*
        For testing relic chances, in case you're paranoid.

        logger.info("USELESS JEWELS CHANCE ROLL: " + chance);
        logger.info("ATTACK_CHANCE = " + (ATTACK_CHANCE));
        logger.info("SKILL_CHANCE = " + (SKILL_CHANCE - ATTACK_CHANCE));
        logger.info("POWER_CHANCE = " + (100 - SKILL_CHANCE));
        */
        if(chance < ATTACK_CHANCE) {
            AbstractDungeon.player.hand.addToHand(new GemstoneAttack());
        } else if(chance < SKILL_CHANCE) {
            AbstractDungeon.player.hand.addToHand(new GemstoneSkill());
        } else {
            AbstractDungeon.player.hand.addToHand(new GemstonePower());
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
