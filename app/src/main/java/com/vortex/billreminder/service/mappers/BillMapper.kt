package com.vortex.billreminder.service.mappers

import com.vortex.billreminder.domain.model.Bill
import com.vortex.billreminder.storage.model.BillStorage

class BillMapper

fun Bill.mapBillStorageToBill(): BillStorage {
    return BillStorage(
        id = id,
        value = value,
        description = description,
        date = date
    )
}

fun BillStorage.mapBillToBillStorage(): Bill {
    return Bill(
        id = id,
        value = value,
        description = description,
        date = date
    )
}