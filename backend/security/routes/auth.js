'use strict'

var express = require('express')
var AuthController = require('../controllers/auth')
var PurchaseOrder = require('../controllers/purchaseOrder')
var CompanyAreas = require('../controllers/company-areas')
const CVrequest = require('../controllers/cvrequest');
var auth = require('../middlewares/auth')
var multipart = require('connect-multiparty');
const { route } = require('../app')
var multipartMiddleware = multipart({ uploadDir: './uploads'});

var router = express.Router();

//Rutas para el proveedor
router.post('/signup', AuthController.signup);
router.post('/signin', AuthController.signin);

router.post('/uploadPostulante', CVrequest.uploadPostulante);

router.post('/uploadRQ', CVrequest.uploadRQ);
router.post('/private/activas', auth, CVrequest.importRQactivas);
router.post('/private/archivadas', auth, CVrequest.importRQarchivadas);
router.post('/private/busqueda', auth, CVrequest.importCV);

router.post('/uploadXMLfile/:nuOC', multipartMiddleware, PurchaseOrder.uploadXMLfile);
router.post('/uploadPDFfile/:nuOC', multipartMiddleware, PurchaseOrder.uploadPDFfile);


router.get('/forgotpassword', AuthController.forgotpassword);
router.get('/resetpassword', AuthController.resetpassword);

router.get('/users', AuthController.getusers);

router.post('/private/providerActivas', auth, PurchaseOrder.importPObyProviderA);
router.post('/private/providerHistoricas', auth, PurchaseOrder.importPObyProviderH);
router.post('/updatePOstatus', PurchaseOrder.updatePOstatus);


//Rutas para las areas internar
router.post('/uploadPO', PurchaseOrder.uploadPO);

router.post('/private/pendientes', auth, PurchaseOrder.importPObyUserP);


router.get('/importPObyNo', PurchaseOrder.importPObyNo);

router.post('/createArea', CompanyAreas.createArea);


module.exports = router;