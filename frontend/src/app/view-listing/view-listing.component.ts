import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { AuctionItem } from '../model';

@Component({
  selector: 'app-view-listing',
  templateUrl: './view-listing.component.html',
  styleUrls: ['./view-listing.component.scss']
})
export class ViewListingComponent implements OnInit {
  myParam: string;
  auctionItem: AuctionItem;

  constructor(private route: ActivatedRoute, private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.myParam = params['id']);
    this.getListing();
  }

  getListing() {
    this.httpClient.get<AuctionItem>('http://localhost:8080/product/'+this.myParam)
      .subscribe((data) => {
        this.auctionItem = data;
      });
  }
}
