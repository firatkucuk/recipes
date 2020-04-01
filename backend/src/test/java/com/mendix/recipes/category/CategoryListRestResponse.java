package com.mendix.recipes.category;

import com.mendix.recipes.common.ResponseType;
import com.mendix.recipes.common.RestResponse;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

class CategoryListRestResponse extends RestResponse<List<CategoryListItemImpl>> {

    public CategoryListRestResponse(final ResponseType type, final String msgId) {
        super(type, msgId);
    }
}

@Data
@Builder
class CategoryListItemImpl implements CategoryListItem {

    private String name;
    private UUID   uuid;
}
