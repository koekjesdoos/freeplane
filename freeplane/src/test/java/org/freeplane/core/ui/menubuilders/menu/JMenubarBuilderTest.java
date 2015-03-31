package org.freeplane.core.ui.menubuilders.menu;

import static org.junit.Assert.assertThat;

import org.freeplane.core.ui.components.FreeplaneMenuBar;
import org.freeplane.core.ui.components.FreeplaneToolBar;
import org.freeplane.core.ui.menubuilders.generic.Entry;
import org.freeplane.core.ui.menubuilders.generic.EntryAccessor;
import org.freeplane.core.ui.menubuilders.menu.JMenubarBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class JMenubarBuilderTest {


	@Test
	public void createsEmptyToolbarComponent() {
		Entry toolbarEntry = new Entry();
		
		final JMenubarBuilder toolbarBuilder = new JMenubarBuilder();
		toolbarBuilder.visit(toolbarEntry);

		assertThat(new EntryAccessor().getComponent(toolbarEntry).getClass(), CoreMatchers.<Object>is(FreeplaneMenuBar.class));
	}
}
