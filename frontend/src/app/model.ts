export class AuctionItem {
  id: number;
  title: string;
  category: string;
  images?: string[];
  descriptiopn: string;
  startingPrice: number;
  currentPrice?: number;
  endTime: Date;
  userEmail?: string;
}

export class User {
  id: number;
  username: string;
  password: string;
  name: string;
}
