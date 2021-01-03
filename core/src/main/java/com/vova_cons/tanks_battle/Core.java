package com.vova_cons.tanks_battle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.vova_cons.tanks_battle.screens.IntroScreen;
import com.vova_cons.tanks_battle.screens.MainMenuScreen;
import com.vova_cons.tanks_battle.screens.ScreenType;
import com.vova_cons.tanks_battle.screens.game.GameScreen;
import com.vova_cons.tanks_battle.screens.settings.SettingsScreen;
import com.vova_cons.tanks_battle.services.AssetsService;
import com.vova_cons.tanks_battle.services.GameSpeedService;
import com.vova_cons.tanks_battle.services.ScreensService;
import com.vova_cons.tanks_battle.services.ServiceLocator;
import com.vova_cons.tanks_battle.services.fonts_service.FontsService;
import com.vova_cons.tanks_battle.services.fonts_service.FontsServiceV2;
import com.vova_cons.tanks_battle.services.settings.SettingsService;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game {
	private float speed = 1f;
	private Screen nextScreen = null;

	@Override
	public void create() {
		registerServices();
		registerScreens();
	}

	private void registerServices() {
		ServiceLocator.register(FontsService.class, new FontsServiceV2());
		ServiceLocator.register(GameSpeedService.class, new GameSpeedService(this));
		ServiceLocator.register(AssetsService.class, new AssetsService());
		ServiceLocator.register(ScreensService.class, new ScreensService(this));
		ServiceLocator.register(SettingsService.class, new SettingsService());
	}

	private void registerScreens() {
		ScreensService screensService = ServiceLocator.getService(ScreensService.class);
		screensService.registerScreen(new IntroScreen());
		screensService.registerScreen(new MainMenuScreen());
		screensService.registerScreen(new GameScreen());
		screensService.registerScreen(new SettingsScreen());
		screensService.changeScreen(ScreenType.Intro);
	}

	@Override
	public void render() {
		float delta = speed * Gdx.graphics.getDeltaTime();
		ServiceLocator.update(delta);
		if (screen != null) {
			screen.render(delta);
		}
		if (nextScreen != null) {
			Screen screenTmp = nextScreen;
			nextScreen = null;
			super.setScreen(screenTmp);
		}
	}

	@Override
	public void setScreen(Screen screen) {
		this.nextScreen = screen;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}