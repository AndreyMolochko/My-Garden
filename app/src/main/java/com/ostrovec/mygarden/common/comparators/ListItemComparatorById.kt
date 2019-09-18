package com.ostrovec.mygarden.common.comparators

import com.ostrovec.mygarden.room.model.ListItem

class ListItemComparatorById : Comparator<ListItem> {
    override fun compare(o1: ListItem?, o2: ListItem?): Int {
        if (o1 != null && o2 != null) {
            return o1.id.compareTo(o2.id)
        }

        return 0

    }
}