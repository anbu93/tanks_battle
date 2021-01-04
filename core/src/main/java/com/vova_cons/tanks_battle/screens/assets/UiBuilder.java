package com.vova_cons.tanks_battle.screens.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.vova_cons.tanks_battle.screens.UI;
import com.vova_cons.tanks_battle.services.AssetsService;
import com.vova_cons.tanks_battle.services.ServiceLocator;
import com.vova_cons.tanks_battle.services.fonts_service.FontsService;
import com.vova_cons.tanks_battle.utils.ViewUtils;

public class UiBuilder extends ViewUtils {
    public static final String BLACK = "ui/black.png";
    public static String BLUE_BUTTON_IMG = "ui/blue_button05.png";
    public static String GREEN_BUTTON_IMG = "ui/green_button05.png";
    public static String PANEL = "ui/grey_panel.png";
    public static String PANEL_GREEN = "ui/green_panel.png";
    public static String CLOSE_BLUE_CROSS = "ui/blue_cross.png";
    public static String CLOSE_RED_BG = "ui/red_button05.png";

    public static ImageTextButton createButton(String text, FontsService.Size size) {
        return createButton(text, size, Color.RED);
    }
    public static ImageTextButton createButton(String text, FontsService.Size size, Color color) {
        FontsService fontsService = ServiceLocator.getService(FontsService.class);
        BitmapFont font = fontsService.getFont(size);
        Drawable button = new TextureRegionDrawable(new TextureRegion(getTexture(BLUE_BUTTON_IMG)));
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(button, button, button, font);
        style.fontColor = color;
        return new ImageTextButton(text, style);
    }

    public static Actor createFade(float alpha) {
        Image fade = new Image(getTexture(BLACK));
        fade.setSize(UI.SCENE_WIDTH, UI.SCENE_HEIGHT);
        fade.getColor().a = alpha;
        return fade;
    }

    public static Actor createPanel() {
        return new Image(new NinePatch(getTexture(PANEL), 20, 20, 20, 20));
    }

    public static Actor createPanelGreen() {
        return new Image(new NinePatch(getTexture(PANEL_GREEN), 20, 20, 20, 20));
    }

    public static Actor createCloseButton() {
        Group group = new Group();
        Image bg = new Image(getTexture(CLOSE_RED_BG));
        group.setSize(bg.getWidth(), bg.getHeight());
        group.addActor(bg);
        Image cross = new Image(getTexture(CLOSE_BLUE_CROSS));
        cross.setPosition(bg.getWidth()/2f, bg.getHeight()/2f, Align.center);
        group.addActor(cross);
        return group;
    }


    public static Texture getTexture(String path) {
        AssetsService assetsService = ServiceLocator.getService(AssetsService.class);
        return assetsService.get(path, Texture.class);
    }
}
