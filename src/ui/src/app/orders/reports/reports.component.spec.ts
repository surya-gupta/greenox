import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderReportsComponent } from './reports.component';

describe('OrderReportsComponent', () => {
  let component: OrderReportsComponent;
  let fixture: ComponentFixture<OrderReportsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderReportsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
