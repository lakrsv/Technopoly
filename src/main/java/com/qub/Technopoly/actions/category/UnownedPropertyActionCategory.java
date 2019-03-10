package com.qub.Technopoly.actions.category;

import com.qub.Technopoly.actions.action.Action;
import com.qub.Technopoly.actions.action.AuctionPropertyAction;
import com.qub.Technopoly.actions.action.BuyPropertyAction;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.util.Field;
import lombok.NonNull;

import static com.qub.Technopoly.io.IOHelper.DoActionDelay;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class UnownedPropertyActionCategory implements ActionCategory {

    private static final String DESCRIPTION_FIELD_FORMAT = "Welcome to %s!";
    private static final String DESCRIPTION_PROPERTY_FORMAT = "You are currently in %s";

    private static final String DESCRIPTION_TWO_FORMAT = "You can buy this property for %s %s.";

    private final OutputSource outputSource = IOHelper.getOutputSource();

    @NonNull
    private final Property property;

    private final Action[] actions;

    public UnownedPropertyActionCategory(Actor actor, Property property, Board board) {
        requireNonNull(actor);
        requireNonNull(property);

        this.property = property;

        actions =
            new Action[] {new BuyPropertyAction(actor, property), new AuctionPropertyAction(property, board)};
    }

    @Override
    public void describe() {
        var field = Field.getFieldForProperty(property);
        outputSource.writeTitle(format(DESCRIPTION_FIELD_FORMAT, field.getName()));
        outputSource.writeBody(field.getDescription());

        DoActionDelay();

        outputSource.writeBody(format(DESCRIPTION_PROPERTY_FORMAT, property.getName()));
        outputSource.writeBody(property.getDescription());

        DoActionDelay();

        outputSource.writeBody(format(DESCRIPTION_TWO_FORMAT, property.getPrice(),
            Config.getConfig().getInventoryConfig().getCurrencyName()));

        describeActions();
    }

    @Override
    public Action[] getActions() {
        return actions;
    }
}
