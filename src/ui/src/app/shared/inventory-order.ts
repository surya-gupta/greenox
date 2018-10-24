import { CategorizedItem } from "./categorized-item";

export class InventoryOrder{
    id: string
    invNum: number
    note: string
    categorisedItems : CategorizedItem[]
    
    // Workflow Data
    completionTime: Date
    status: string

    paymentMode: string
    // Calculation Fields
    netAmount: number
    advanceAmount: number
    pendingAmount: number

    // Audit Fields
    addedBy: string
    updatedBy: string
    entryTime: Date
    updateTime: Date
}