import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCashierComponent } from './add-cashier.component';

describe('AddCashierComponent', () => {
  let component: AddCashierComponent;
  let fixture: ComponentFixture<AddCashierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCashierComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCashierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
