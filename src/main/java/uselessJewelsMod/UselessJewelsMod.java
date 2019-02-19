package uselessJewelsMod;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import uselessJewelsMod.cards.GemstoneAttack;
import uselessJewelsMod.cards.GemstonePower;
import uselessJewelsMod.cards.GemstoneSkill;
import uselessJewelsMod.relics.UselessJewelsRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uselessJewelsMod.util.TextureLoader;

@SpireInitializer
public class UselessJewelsMod implements EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, PostInitializeSubscriber {

    public static final Logger logger = LogManager.getLogger(UselessJewelsMod.class.getName());
    private static String modID;

    // For in-game mod settings.
    private static final String MODNAME = "Useless Jewels";
    private static final String AUTHOR = "dud95";
    private static final String DESCRIPTION = "A simple mod that introduces one new relic, the Useless Jewels, and three related cards.";

    // Textures

    // Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "uselessJewelsModResources/images/Badge.png";

    //Texture loading helper methods
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public UselessJewelsMod() {
        logger.info("Subscribing to BaseMod hooks.");
        BaseMod.subscribe(this);
        setModID("uselessJewelsMod");
        logger.info("Finished subscribing to BaseMod.");
    }

    public static String getModID() {
        return modID;
    }

    public static void setModID(String ID) {
        modID = ID;
    }



    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("Initializing Useless Jewels mod.");
        UselessJewelsMod uselessJewelsMod = new UselessJewelsMod();
        logger.info("Initialized Useless Jewels mod.");
    }

    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options.");
        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("No settings are currently available for Useless Jewels.", 400.0f, 700.0f,
                settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done loading badge Image and mod options.");
    }


    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics.");

        BaseMod.addRelic(new UselessJewelsRelic(), RelicType.SHARED);
        UnlockTracker.markRelicAsSeen(UselessJewelsRelic.ID);

        logger.info("Done adding relics!");
    }

    @Override
    public void receiveEditCards() {
//        logger.info("Adding variables");
//        // Add the Custom Dynamic Variables
//        logger.info("Add variabls");
//        // Add the Custom Dynamic variabls
//        BaseMod.addDynamicVariable(new DefaultCustomVariable());
//        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());

        logger.info("Adding cards.");
        BaseMod.addCard(new GemstoneAttack());
        BaseMod.addCard(new GemstoneSkill());
//        BaseMod.addCard(new GemstonePower());

        logger.info("Unlocking cards.");        // Unlock the cards, so that they are all "seen" in the library
        UnlockTracker.unlockCard(GemstoneAttack.ID);
        UnlockTracker.unlockCard(GemstoneSkill.ID);
//        UnlockTracker.unlockCard(GemstonePower.ID);


        logger.info("Done adding cards!");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings.");

        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/UselessJewelsMod-Card-Strings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/UselessJewelsMod-Relic-Strings.json");

        logger.info("Done editing strings.");
    }

    // Utility method to build unique IDs for modded objects.
    public static String makeID(String id) {
        return (getModID() + ":" + id);
    }
}
