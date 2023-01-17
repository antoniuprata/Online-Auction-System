import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map, first } from 'rxjs';
import { AuthenticationService } from '../authentication.service';
import { AuctionItem } from '../model';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.scss']
})
export class WatchlistComponent implements OnInit {
  auctionItems: AuctionItem[];
  currentUser: any;

  constructor(private httpClient: HttpClient, private router: Router, private authentificationService: AuthenticationService) { }

  ngOnInit(): void {
    this.currentUser = this.authentificationService.currentUserValue;
    this.getAuctionItems();
  }

  getAuctionItems() {
    this.httpClient.get<AuctionItem[]>('http://localhost:8080/watchlist/'+this.currentUser.email)
      .subscribe((data) => {
        this.auctionItems = data;
        this.auctionItems.filter(auctionItem => auctionItem.endTime = new Date(auctionItem.endTime.toString()));
      });
  }

  viewListing(id: number) {
    this.router.navigate(['/view-listing', id]);
  }

}
