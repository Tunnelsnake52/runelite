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

import java.awt.*;
import javax.inject.Inject;

import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.queries.InventoryWidgetItemQuery;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.*;
import net.runelite.client.util.QueryRunner;

public class RcbooksOverlay extends Overlay
{
	private final QueryRunner queryRunner;
	private final Client client;
	private final RcbooksConfig config;

	boolean allBooks;

	@Inject
	private RcbooksOverlay(QueryRunner queryRunner, Client client, RcbooksConfig config)
	{
		this.client = client;
		this.config = config;
		this.queryRunner = queryRunner;

		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
		setPriority(OverlayPriority.LOW);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		Books currentBook = null;
		for (int i=1;i<16;i++) {
			currentBook = Books.getBook(i);
			Query query = new InventoryWidgetItemQuery().idEquals(currentBook.getItemId());
			WidgetItem[] item = queryRunner.runQuery(query);
			if (item.length == 0)
			{
				allBooks=false;
				break;
			}
			if(i==15 & item.length ==1){
				allBooks = true;
			}
			if (i==16) {
				return null;
			}
		}

		if(!allBooks)
		{
			if (currentBook.getId() == 5)
			{
				WorldPoint bookLocation = new WorldPoint(1630, 3800, 0);
				drawTile(graphics, bookLocation);
			}
			else if (currentBook.getId() == 15)
			{
				WorldPoint bookLocation = new WorldPoint(1640, 3800, 0);
				drawTile(graphics, bookLocation);
			}
			else if (currentBook.getId() % 2 == 0)
			{
				System.out.println(currentBook.getId());
				WorldPoint bookLocation = new WorldPoint(1626 + currentBook.getId() - 1, 3801, 0);
				drawTile(graphics, bookLocation);
			}
			else
			{
				WorldPoint bookLocation = new WorldPoint(1626 + (currentBook.getId() - 1), 3799, 0);
				drawTile(graphics, bookLocation);
			}
		}
		return null;
	}

	private void drawTile(Graphics2D graphics, WorldPoint point)
	{
		WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();

		if (point.distanceTo(playerLocation) >= 32)
		{
			return;
		}

		LocalPoint lp = LocalPoint.fromWorld(client, point);
		if (lp == null)
		{
			return;
		}

		Polygon poly = Perspective.getCanvasTilePoly(client, lp);
		if (poly == null)
		{
			return;
		}

		OverlayUtil.renderPolygon(graphics, poly, config.tileColor());
	}
}
