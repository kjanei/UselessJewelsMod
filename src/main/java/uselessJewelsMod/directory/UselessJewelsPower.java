package uselessJewelsMod.directory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import uselessJewelsMod.UselessJewelsMod;
import uselessJewelsMod.cards.GemstoneAttack;
import uselessJewelsMod.cards.GemstoneSkill;
import uselessJewelsMod.util.TextureLoader;

import java.util.Iterator;

import static uselessJewelsMod.UselessJewelsMod.makePowerPath;

public class UselessJewelsPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = UselessJewelsMod.makeID("UselessJewelsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public UselessJewelsPower(final AbstractCreature owner, final AbstractCreature source, final int amount){
        this.name = NAME;
        this.ID = ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        this.isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    private void updateExistingGemstones() {
        Iterator<AbstractCard> iterator = AbstractDungeon.player.hand.group.iterator();

        this.updateCardsInCollection(iterator);

        iterator = AbstractDungeon.player.drawPile.group.iterator();

        this.updateCardsInCollection(iterator);

        iterator = AbstractDungeon.player.discardPile.group.iterator();

        this.updateCardsInCollection(iterator);

        iterator = AbstractDungeon.player.exhaustPile.group.iterator();

        this.updateCardsInCollection(iterator);

    }

    private void updateCardsInCollection(Iterator<AbstractCard> iterator){
        while(iterator.hasNext()) {
            AbstractCard c = iterator.next();
            if (c instanceof GemstoneAttack) {
                c.baseDamage = GemstoneAttack.DAMAGE + this.amount;
            } else if (c instanceof GemstoneSkill) {
                c.baseBlock = GemstoneSkill.BLOCK + this.amount;
            }
        }
    }

    @Override
    public void onDrawOrDiscard(){
        Iterator<AbstractCard> iterator = AbstractDungeon.player.hand.group.iterator();

        this.updateCardsInCollection(iterator);
    }

}
