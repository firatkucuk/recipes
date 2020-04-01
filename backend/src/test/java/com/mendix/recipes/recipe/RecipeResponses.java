package com.mendix.recipes.recipe;

import com.mendix.recipes.common.ResponseType;
import com.mendix.recipes.common.RestResponse;
import java.util.List;

class RecipeAddRestResponse extends RestResponse<RecipeInfoImpl> {

    public RecipeAddRestResponse(final ResponseType type, final String msgId) {
        super(type, msgId);
    }
}

class RecipeListRestResponse extends RestResponse<List<RecipeInfoImpl>> {

    public RecipeListRestResponse(final ResponseType type, final String msgId) {
        super(type, msgId);
    }
}
