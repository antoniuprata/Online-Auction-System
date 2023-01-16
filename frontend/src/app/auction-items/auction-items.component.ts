import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuctionItem } from '../model';

@Component({
  selector: 'auction-items',
  templateUrl: './auction-items.component.html',
  styleUrls: ['./auction-items.component.scss']
})
export class AuctionItemsComponent implements OnInit {
  auctionItems: AuctionItem[];

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.getAuctionItems();
  }

  getAuctionItems() {
    this.httpClient.get<AuctionItem[]>('http://localhost:8080/product')
      .subscribe((data) => {
        this.auctionItems = data;
        console.log(this.auctionItems);
      });
  }

}
