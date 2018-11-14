/*
 * Copyright (c) 2017, Tyler <https://github.com/tylerthardy>
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


import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Query;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.queries.InventoryItemQuery;
import net.runelite.api.queries.InventoryWidgetItemQuery;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.util.QueryRunner;

import static net.runelite.api.ItemID.HOSIDIUS_LETTER;
import static net.runelite.api.ItemID.EATHRAM__RADA_EXTRACT;
import static net.runelite.api.ItemID.RADAS_CENSUS;
import static net.runelite.api.ItemID.RICKTORS_DIARY_7;
import static net.runelite.api.ItemID.KILLING_OF_A_KING;
import static net.runelite.api.ItemID.IDEOLOGY_OF_DARKNESS;
import static net.runelite.api.ItemID.RADAS_JOURNEY;
import static net.runelite.api.ItemID.TWILL_ACCORD;
import static net.runelite.api.ItemID.WINTERTODT_PARABLE;
import static net.runelite.api.ItemID.TRISTESSAS_TRAGEDY;
import static net.runelite.api.ItemID.TRANSVERGENCE_THEORY;
import static net.runelite.api.ItemID.TREACHERY_OF_ROYALTY;
import static net.runelite.api.ItemID.TRANSPORTATION_INCANTATIONS;
import static net.runelite.api.ItemID.BYRNES_CORONATION_SPEECH;
import static net.runelite.api.ItemID.SOUL_JOURNEY;


public enum Books
{
	LETTER(1, HOSIDIUS_LETTER, 0),
	EXTRACT(2, EATHRAM__RADA_EXTRACT, 0),
	CENSUS(3, RADAS_CENSUS, 0),
	RICK(4, RICKTORS_DIARY_7, 0),
	KING(5, KILLING_OF_A_KING, 0),
	IDEO(6, IDEOLOGY_OF_DARKNESS, 0),
	RADA(7, RADAS_JOURNEY, 0),
	TWILL(8, TWILL_ACCORD, 0),
	TODT(9, WINTERTODT_PARABLE, 0),
	TRIST(10, TRISTESSAS_TRAGEDY, 0),
	TRANS(11, TRANSVERGENCE_THEORY, 0),
	TREACH(12, TREACHERY_OF_ROYALTY, 0),
	INCANT(13, TRANSPORTATION_INCANTATIONS, 0),
	BYRNE(14, BYRNES_CORONATION_SPEECH, 0),
	SOUL(15, SOUL_JOURNEY, 0);

	@Getter
	private final int id;
	@Getter
	private final int itemId;

    @Getter
    private final int count;

	@Getter
	@Setter
	private BufferedImage image;

	private static final Map<Integer, Books> books = new HashMap<>();

	static
	{
		for (Books book : values())
		{
			books.put(book.getId(), book);
		}
	}

	Books(int id, int itemId, int count)
	{
		this.id = id;
		this.itemId = itemId;
        this.count = count;
	}

	public static Books getBook(int varbit)
	{
		return books.get(varbit);
	}

}
