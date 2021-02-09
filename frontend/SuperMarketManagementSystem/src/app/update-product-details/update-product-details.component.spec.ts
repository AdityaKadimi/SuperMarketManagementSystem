import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateProductDetailsComponent } from './update-product-details.component';

describe('UpdateProductDetailsComponent', () => {
  let component: UpdateProductDetailsComponent;
  let fixture: ComponentFixture<UpdateProductDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateProductDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateProductDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
