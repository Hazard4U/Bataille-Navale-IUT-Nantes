/**
 * Express Router configuration
 */
const express = require('express');
const router = express.Router();

/* API routes */
router.use('/joueur', require('./api/joueurRoutes'));
router.use('/partie', require('./api/partieRoutes'));

module.exports = router;