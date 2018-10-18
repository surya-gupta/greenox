import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderOpenComponent } from './open.component';

describe('OrderOpenComponent', () => {
  let component: OrderOpenComponent;
  let fixture: ComponentFixture<OrderOpenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderOpenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderOpenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
