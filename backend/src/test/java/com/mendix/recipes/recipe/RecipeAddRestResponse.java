package com.mendix.recipes.recipe;

import com.mendix.recipes.common.ResponseType;
import com.mendix.recipes.common.RestResponse;

class RecipeAddRestResponse extends RestResponse<RecipeInfoImpl> {

    public RecipeAddRestResponse(final ResponseType type, final String msgId) {
        super(type, msgId);
    }
}
