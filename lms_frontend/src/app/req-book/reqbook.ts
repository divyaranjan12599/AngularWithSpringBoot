export enum isActive{
    Cancelled,
    Pending,
    Pending_Purchase,
    Approved
}

export class requestbookdetails{
    constructor(
    requestBookId:number,
    authorName:string,
    bookName:string,
    isActive:isActive,
    ){}
}

export interface requestbookdto{
    requestId:number;
    authorName: string;
    bookName: string;
    isActive: string;
}