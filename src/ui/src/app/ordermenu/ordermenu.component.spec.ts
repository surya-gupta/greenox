import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdermenuComponent } from './ordermenu.component';

describe('OrdermenuComponent', () => {
  let component: OrdermenuComponent;
  let fixture: ComponentFixture<OrdermenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdermenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdermenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
