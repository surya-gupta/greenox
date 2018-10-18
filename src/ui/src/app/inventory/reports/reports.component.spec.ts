import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryReportsComponent } from './reports.component';

describe('InventoryReportsComponent', () => {
  let component: InventoryReportsComponent;
  let fixture: ComponentFixture<InventoryReportsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InventoryReportsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
