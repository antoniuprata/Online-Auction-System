import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { first, map } from 'rxjs';
import { AuthenticationService } from '../authentication.service';
import { AuctionItem } from '../model';

@Component({
  selector: 'auction-items',
  templateUrl: './auction-items.component.html',
  styleUrls: ['./auction-items.component.scss']
})
export class AuctionItemsComponent implements OnInit {
  auctionItems: AuctionItem[];
  currentUser: any;

  constructor(private httpClient: HttpClient, private router: Router, private authentificationService: AuthenticationService) { }

  ngOnInit(): void {
    this.currentUser = this.authentificationService.currentUserValue;
    this.getAuctionItems();
  }

  getAuctionItems() {
    this.httpClient.get<AuctionItem[]>('http://localhost:8080/product')
      .subscribe((data) => {
        this.auctionItems = data;
      });
  }

  viewListing(id: number) {
    this.router.navigate(['/view-listing', id]);
  }

  addToWatchlist(id: number) {
    return this.httpClient.post<any>('http://localhost:8080/watchlist', { emailUser: this.currentUser.email, id: id, })
    .pipe(map(data => {
      //this.authentificationService.reloadPage();
      return data;
    }))
    .pipe(first())
    .subscribe(
      (data) => {
      },
      (error) => {
      }
    );
  }

}
