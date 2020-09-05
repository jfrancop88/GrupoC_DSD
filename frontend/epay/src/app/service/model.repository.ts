import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { RestDataSource } from './rest.datasource';

@Injectable()
export class ProviderARepository {
    models: any[] = [];
    amount: number = 0;
    constructor(private dataSource: RestDataSource) {
        this.dataSource.getModelsByProvider('providerActivas').subscribe(data => {
            this.models = data;
        });
        this.dataSource.getAmountOfProvider('providerActivas').subscribe(data => {
            this.amount = data.amount;
        });
    }
    getModelsByProvider(): any[] {
        return this.models;
    }
    getAmountOfProvider(): number {
        return this.amount;
    }
    getModel(id: string): any {
        return this.models.find(p => p._id == id);
    }
    getModelFromServer(model: string, id: string): Observable<any> {
        return this.dataSource.getModel(model, id);
    }

    saveModel(modelName: string, model: any) {
        this.dataSource.saveModel(modelName, model)
            .subscribe(p => {
                this.models.push(p);
                this.amount = this.amount + 1;
            });
    }
    updateModel(modelName: string, model: any) {
        this.dataSource.updateModel(modelName, model)
            .subscribe(p => { this.models.splice(this.models.findIndex(p => p._id == model._id), 1, model);});
    }
    deleteModel(modelName: string, id: string) {
        this.dataSource.deleteModel(modelName, id).subscribe(p => {
            this.models.splice(this.models.findIndex(p => p._id == id), 1);
            this.amount = this.amount - 1;
        });
    }
}

@Injectable()
export class ProviderHRepository {
    models: any[] = [];
    amount: number = 0;
    constructor(private dataSource: RestDataSource) {
        this.dataSource.getModelsByProvider('providerHistoricas').subscribe(data => {
            this.models = data;
        });
        this.dataSource.getAmountOfProvider('providerHistoricas').subscribe(data => {
            this.amount = data.amount;
        });
    }
    getModelsByProvider(): any[] {
        return this.models;
    }
    getAmountOfProvider(): number {
        return this.amount;
    }
    getModel(id: string): any {
        return this.models.find(p => p._id == id);
    }
    getModelFromServer(model: string, id: string): Observable<any> {
        return this.dataSource.getModel(model, id);
    }

    saveModel(modelName: string, model: any) {
        this.dataSource.saveModel(modelName, model)
            .subscribe(p => {
                this.models.push(p);
                this.amount = this.amount + 1;
            });
    }
    updateModel(modelName: string, model: any) {
        this.dataSource.updateModel(modelName, model)
            .subscribe(p => { this.models.splice(this.models.findIndex(p => p._id == model._id), 1, model);});
    }
    deleteModel(modelName: string, id: string) {
        this.dataSource.deleteModel(modelName, id).subscribe(p => {
            this.models.splice(this.models.findIndex(p => p._id == id), 1);
            this.amount = this.amount - 1;
        });
    }
}

@Injectable()
export class Activas {
    models: any[] = [];
    amount: number = 0;
    constructor(private dataSource: RestDataSource) {
        this.dataSource.getModels('activas').
        subscribe(data => {
            this.models = data;
        });
        this.dataSource.getAmount('activas').subscribe(data => {
            this.amount = data.amount;
        });
    }
    getModels(): any[] {
        return this.models;
    }
    getAmount(): number {
        return this.amount;
    }
    getModel(id: string): any {
        return this.models.find(p => p._id == id);
    }
    getModelFromServer(model: string, id: string): Observable<any> {
        return this.dataSource.getModel(model, id);
    }

    saveModel(modelName: string, model: any) {
        this.dataSource.saveModel(modelName, model)
            .subscribe(p => {
                this.models.push(p);
                this.amount = this.amount + 1;
            });
    }
    updateModel(modelName: string, model: any) {
        this.dataSource.updateModel(modelName, model)
            .subscribe(p => { this.models.splice(this.models.findIndex(p => p._id == model._id), 1, model);});
    }
    deleteModel(modelName: string, id: string) {
        this.dataSource.deleteModel(modelName, id).subscribe(p => {
            this.models.splice(this.models.findIndex(p => p._id == id), 1);
            this.amount = this.amount - 1;
        });
    }
}

@Injectable()
export class Archivadas {
    models: any[] = [];
    amount: number = 0;
    constructor(private dataSource: RestDataSource) {
        this.dataSource.getModels('archivadas').
        subscribe(data => {
            this.models = data;
        });
        this.dataSource.getAmount('archivadas').subscribe(data => {
            this.amount = data.amount;
        });
    }
    getModels(): any[] {
        return this.models;
    }
    getAmount(): number {
        return this.amount;
    }
    getModel(id: string): any {
        return this.models.find(p => p._id == id);
    }
    getModelFromServer(model: string, id: string): Observable<any> {
        return this.dataSource.getModel(model, id);
    }

    saveModel(modelName: string, model: any) {
        this.dataSource.saveModel(modelName, model)
            .subscribe(p => {
                this.models.push(p);
                this.amount = this.amount + 1;
            });
    }
    updateModel(modelName: string, model: any) {
        this.dataSource.updateModel(modelName, model)
            .subscribe(p => { this.models.splice(this.models.findIndex(p => p._id == model._id), 1, model);});
    }
    deleteModel(modelName: string, id: string) {
        this.dataSource.deleteModel(modelName, id).subscribe(p => {
            this.models.splice(this.models.findIndex(p => p._id == id), 1);
            this.amount = this.amount - 1;
        });
    }
}

@Injectable()
export class Busqueda {
    models: any[] = [];
    amount: number = 0;
    constructor(private dataSource: RestDataSource) {
        this.dataSource.getModels('busqueda').
        subscribe(data => {
            this.models = data;
        });
        this.dataSource.getAmount('busqueda').subscribe(data => {
            this.amount = data.amount;
        });
    }
    getModels(): any[] {
        return this.models;
    }
    getAmount(): number {
        return this.amount;
    }
    getModel(id: string): any {
        return this.models.find(p => p._id == id);
    }
    getModelFromServer(model: string, id: string): Observable<any> {
        return this.dataSource.getModel(model, id);
    }

    saveModel(modelName: string, model: any) {
        this.dataSource.saveModel(modelName, model)
            .subscribe(p => {
                this.models.push(p);
                this.amount = this.amount + 1;
            });
    }
    updateModel(modelName: string, model: any) {
        this.dataSource.updateModel(modelName, model)
            .subscribe(p => { this.models.splice(this.models.findIndex(p => p._id == model._id), 1, model);});
    }
    deleteModel(modelName: string, id: string) {
        this.dataSource.deleteModel(modelName, id).subscribe(p => {
            this.models.splice(this.models.findIndex(p => p._id == id), 1);
            this.amount = this.amount - 1;
        });
    }
}
