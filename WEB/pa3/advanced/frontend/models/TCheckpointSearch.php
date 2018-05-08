<?php

namespace frontend\models;

use Yii;
use yii\base\Model;
use yii\data\ActiveDataProvider;
use frontend\models\TCheckpoint;

/**
 * TCheckpointSearch represents the model behind the search form about `frontend\models\TCheckpoint`.
 */
class TCheckpointSearch extends TCheckpoint
{
    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['checkpoint_id', 'location_id'], 'integer'],
            [['checkpoint_name', 'path_gambarpoint'], 'safe'],
            [['latitude', 'longitude'], 'number'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     *
     * @return ActiveDataProvider
     */
    public function search($params)
    {
        $query = TCheckpoint::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'checkpoint_id' => $this->checkpoint_id,
            'location_id' => $this->location_id,
        ]);

        $query->andFilterWhere(['like', 'checkpoint_name', $this->checkpoint_name]);

        return $dataProvider;
    }
}
