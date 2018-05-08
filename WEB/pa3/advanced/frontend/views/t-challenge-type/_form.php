<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use frontend\models\TCheckpoint;
use kartik\select2\Select2;

/* @var $this yii\web\View */
/* @var $model frontend\models\TChallengeType */
/* @var $form yii\widgets\ActiveForm */
?>

<div class="tchallenge-type-form">

    <?php $form = ActiveForm::begin(); ?>

    <!--?= $form->field($model, 'checkpoint_id')->textInput() ?-->
    <!--?= $form->field($model, 'checkpoint_id')->dropDownList(ArrayHelper::map(TCheckpoint::find()->all(),'checkpoint_id', 'checkpoint_name'),['prompt'=>'-Chose Checkpoint-'])?-->

    <?= $form->field($model, 'type_name')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'description')->textArea() ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
