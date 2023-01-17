import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuctionItem } from '../model';

@Component({
  selector: 'app-browse-paintings',
  templateUrl: './browse-paintings.component.html',
  styleUrls: ['./browse-paintings.component.scss']
})
export class BrowsePaintingsComponent implements OnInit {
  auctionItems: AuctionItem[];

  constructor(private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.getAuctionItems();
  }

  getAuctionItems() {
    this.httpClient.get<AuctionItem[]>('http://localhost:8080/product')
      .subscribe((data) => {
        this.auctionItems = data;
        this.auctionItems.filter(auctionItem => auctionItem.endTime = new Date(auctionItem.endTime.toString()));
      });
  }

  viewListing(id: number) {
    this.router.navigate(['/view-listing', id]);
  }

}
