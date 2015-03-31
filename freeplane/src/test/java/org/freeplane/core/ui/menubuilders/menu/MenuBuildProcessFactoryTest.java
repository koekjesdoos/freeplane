package org.freeplane.core.ui.menubuilders.menu;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import javax.swing.JMenu;

import org.freeplane.core.ui.menubuilders.ResourceAccessorStub;
import org.freeplane.core.ui.menubuilders.XmlEntryStructureBuilder;
import org.freeplane.core.ui.menubuilders.generic.Entry;
import org.freeplane.core.ui.menubuilders.generic.EntryAccessor;
import org.freeplane.core.ui.menubuilders.generic.PhaseProcessor;
import org.freeplane.core.ui.menubuilders.menu.MenuBuildProcessFactory;
import org.freeplane.features.mode.FreeplaneActions;
import org.junit.Test;


public class MenuBuildProcessFactoryTest {
	@Test
	public void ifProcessOnPopupIsSet_delayesActionProcessing() throws Exception {
		final MenuBuildProcessFactory buildProcessFactory = new MenuBuildProcessFactory();
		final FreeplaneActions freeplaneActions = mock(FreeplaneActions.class);
		final PhaseProcessor phaseProcessor = buildProcessFactory.createBuildProcessor(freeplaneActions, new ResourceAccessorStub()) ;
		final Entry menuStructure = XmlEntryStructureBuilder.buildMenuStructure(
				"<Entry builder='main_menu'>"
						+ "<Entry name='submenu'>"
						+ "<Entry name='submenu' processOnPopup='true'>"
						+ "<Entry name='action'/>"
						+ "</Entry>"
						+ "</Entry>"
						+ "</Entry>");
		phaseProcessor.build(menuStructure);
		verify(freeplaneActions, never()).getAction("action");
	}

	@Test
	public void ifProcessOnPopupIsSet_buildsWhenItBecomesVisible() throws Exception {
		final MenuBuildProcessFactory buildProcessFactory = new MenuBuildProcessFactory();
		final FreeplaneActions freeplaneActions = mock(FreeplaneActions.class);
		final PhaseProcessor phaseProcessor = buildProcessFactory.createBuildProcessor(freeplaneActions, new ResourceAccessorStub());
		final Entry menuStructure = XmlEntryStructureBuilder.buildMenuStructure(
				"<Entry builder='main_menu'>"
						+ "<Entry name='submenu'>"
						+ "<Entry name='submenu' processOnPopup='true'>"
						+ "<Entry name='action'/>"
						+ "</Entry>"
						+ "</Entry>"
						+ "</Entry>");
		phaseProcessor.build(menuStructure);
		final Entry openedEntry = menuStructure.getChild(0,0,0);
		JMenu menu = (JMenu) new EntryAccessor().getComponent(openedEntry);
		menu.getPopupMenu().setVisible(true);
		verify(freeplaneActions).getAction("action");
	}
}
