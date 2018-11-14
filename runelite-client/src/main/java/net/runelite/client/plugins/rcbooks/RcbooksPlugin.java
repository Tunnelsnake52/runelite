/*
 * Copyright (c) 2017, Tyler <http://github.com/tylerthardy>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.rcbooks;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Provides;
import javax.inject.Inject;

import joptsimple.internal.Strings;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.Query;
import net.runelite.api.events.GameTick;
import net.runelite.api.queries.EquipmentItemQuery;
import net.runelite.api.queries.InventoryWidgetItemQuery;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.cannon.CannonCounter;
import net.runelite.client.plugins.slayer.TaskCounter;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.QueryRunner;
import net.runelite.client.util.Text;
import java.util.*;

import java.awt.image.BufferedImage;
import java.util.regex.Matcher;

import static com.google.common.collect.ObjectArrays.concat;

@PluginDescriptor(
	name = "RC Books"
)
@Slf4j
public class RcbooksPlugin extends Plugin
{
    int[] count;
    int bookID;
    int currentBook;

    boolean counted = false;

    private static final String COPY = "You already have a copy";

    private RcbooksCounter counter;

    @Inject
    private ItemManager itemManager;

    @Inject
    private Client client;

    @Inject
    private QueryRunner queryRunner;

    @Inject
    private InfoBoxManager infoBoxManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private RcbooksOverlay overlay;

	@Provides
	RcbooksConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RcbooksConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
        count = new int[16];
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}
    @Subscribe
    public void onGameTick(GameTick event)
    {

        Widget NPCDialog = client.getWidget(WidgetInfo.DIALOG_SPRITE_TEXT);
        if (NPCDialog != null)
        {
            String NPCText = Text.removeTags(NPCDialog.getText()); //remove color and linebreaks

            if (NPCText.contains("A letter from Lord Hosidius to the Council of Elders") && !counted && !NPCText.contains(COPY))
            {
                bookID=1;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("An extract from Eathram & Rada") && !counted && !NPCText.contains(COPY))
            {
                bookID=2;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("Census of King Rada III, by Matthias") && !counted && !NPCText.contains(COPY))
            {
                bookID=3;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("Diary of Steklan Ricktor")  && !counted && !NPCText.contains(COPY))
            {
                bookID=4;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("Killing of a King")  && !counted && !NPCText.contains(COPY))
            {
                bookID=5;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("The Ideology of Darknes") && !counted && !NPCText.contains(COPY))
            {
                bookID=6;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("The Journey of Rada")  && !counted && !NPCText.contains(COPY))
            {
                bookID=7;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("The Royal Accord of") && !counted && !NPCText.contains(COPY))
            {
                bookID=8;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("The Parable of the Wintertodt")  && !counted && !NPCText.contains(COPY))
            {
                bookID=9;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("The Tragedy of Tristessa")  && !counted && !NPCText.contains(COPY))
            {
                bookID=10;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("The Theory of Transvergence")  && !counted && !NPCText.contains(COPY))
            {
                bookID=11;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("The Treachery of Royalty")  && !counted && !NPCText.contains(COPY))
            {
                bookID=12;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("Transportation Incantations") && !counted && !NPCText.contains(COPY))
            {
                bookID=13;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }
            if (NPCText.contains("Speech of King Byrne I") && !counted && !NPCText.contains(COPY))
            {
                bookID=14;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }

            if (NPCText.contains("The Journey of Souls, by") && !counted && !NPCText.contains(COPY))
            {
                bookID=15;
                count[bookID]++;
                removeCounter();
                addCounter();
                counted = true;
            }

        }
        else
        {
            counted = false;
        }
    }


    private void addCounter()
    {
        if (counter != null)
        {
            return;
        }

        currentBook = bookID;
        counter = new RcbooksCounter(itemManager.getImage(Books.getBook(bookID).getItemId()), this);
        infoBoxManager.addInfoBox(counter);

    }

    private void removeCounter()
    {
        if (counter == null || currentBook == bookID )
        {
            return;
        }

        infoBoxManager.removeInfoBox(counter);
        counter = null;
    }
}
