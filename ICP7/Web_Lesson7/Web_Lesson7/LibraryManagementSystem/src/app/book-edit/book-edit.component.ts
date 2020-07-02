import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ApiService} from '../api.service';
import {FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})

export class BookEditComponent implements OnInit {
  book = {};
  bookForm: FormGroup;
  isbn: string = '';
  title: string = '';
  description: string = '';
  author: string = '';
  volume_number: string = '';
  book_category: string = '';
  constructor(private router: Router, private route: ActivatedRoute, private api: ApiService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.bookForm = this.formBuilder.group({
      'isbn': [null, Validators.required],
      'title': [null, Validators.required],
      'description': [null, Validators.required],
      'author': [null, Validators.required],
      'volume_number': [null, Validators.required],
      'book_category': [null, Validators.required]
    });
    this.getBook(this.route.snapshot.params['identity']);
  }
  getBookDetails(identity) {
    this.api.getBook(identity)
      .subscribe(data => {
        console.log(data);
        this.book = data;
      });
  }
  onFormSubmit(form: NgForm) {
    let identity = this.route.snapshot.params['id'];
    console.log(form)
    this.api.updateBook(identity, form)
      .subscribe(res => {
        this.router.navigate(['/book-details', identity]);
      }, (err) => {
        console.log(err);
      });
  }
  getBook(identity) {
    this.api.getBook(identity).subscribe(data => {
      identity = data._identity;
      this.bookForm.setValue({
        isbn: data.isbn,
        title: data.title,
        description: data.description,
        author: data.author,
        volume_number: data.volume_number,
        book_category: data.book_category
      });
    });
  }
}
